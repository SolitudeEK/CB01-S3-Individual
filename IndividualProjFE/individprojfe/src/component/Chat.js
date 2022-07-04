import React, { useState } from "react";
import Messaging from "./Messaging.js";
import Token from "../logic/Token.js";
import classes from "../styles/Chat.module.css";
//cypress light house
const Chat = () => {
	var stompClient = null;
	var messages = new Array();
	const [message, setMessage] = useState("");
	const sendHandler = () => {
		if (message !== "") {
			connect();
			const toSend = {
				sender: Token.getLogin(),
				message: message,
			};
			setTimeout(
				() =>
					stompClient.send(
						"/app/message",
						Token.getHeader(),
						JSON.stringify(toSend)
					),
				300
			);
		}
	};
	const connect = () => {
		var SockJS = require("sockjs-client/dist/sockjs.js");
		const Stomp = require("stompjs");
		SockJS = new SockJS("http://localhost:8080/ws");
		stompClient = Stomp.over(SockJS);
		stompClient.connect({}, onConnected, {});
	};
	const onConnected = () => {
		stompClient.subscribe("/user/message/result", onMessageReceived);
		stompClient.subscribe(
			"/user/" + Token.getLogin() + "/result",
			onMessageTokenReceived
		);
	};
	const onMessageReceived = (msg) => {
		var data = JSON.parse(msg.body);
		messages.push(data["sender"]);
		messages.push(data["message"]);
		//stompClient.disconnect();
		console.log(messages.length);
	};
	const onMessageTokenReceived = (msg) => {
		var tokens = JSON.parse(msg.body);
		Token.set(tokens["basic"], tokens["refresh"], Token.getLogin());
		sendHandler();
	};
	return (
		<div>
			<div className={classes.messageBox}> <Messaging text={messages} /></div>
			<input
				className={classes.inputLine}
				onChange={(event) => setMessage(event.target.value)}
			></input>
			<button className={classes.buttonLine} onClick={sendHandler}>
				send{" "}
			</button>
		</div>
	);
};
export default Chat;
