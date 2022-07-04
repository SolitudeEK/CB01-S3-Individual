import classs from "../styles/InputRoom.module.css";
import Token from "../logic/Token.js";
import React, { useState, useEffect } from "react";
import MessageParser from "../logic/MessageParser.js";
import PersonalInfo from "../logic/PersonalInfo.js";
const InputRoom = (props) => {
	var stompClient = null;
	var isSubscribe = 0;
	const [roomNumber, setRN] = useState("");
	const clickHendler = () => {
		if (roomNumber !== "") {
			connect();
			const message = {
				login: Token.getLogin(),
				name: roomNumber,
			};
			setTimeout(() => {
				stompClient.send(
					"/app/mutiplayer/" + props.toSend,
					Token.getHeader(),
					JSON.stringify(message)
				);
			}, 400);
		}
		console.log(props.toSend);
	};
	const connect = () => {
		var SockJS = require("sockjs-client/dist/sockjs.js");
		const Stomp = require("stompjs");
		SockJS = new SockJS("http://localhost:8080/ws");
		stompClient = Stomp.over(SockJS);
		stompClient.connect({}, onConnected, onError);
	};
	const onConnected = () => {
		if (isSubscribe === 0) {
			stompClient.subscribe(
				"/user/room/" + Token.getLogin() + "/result",
				onMessageReceived
			);
			isSubscribe = 1;
		}
	};
	const onError = (err) => {
		console.log("err: " + err);
	};
	const onMessageReceived = (msg) => {
		var result= MessageParser.parse(msg);
		if(result==="Start")
		{
			PersonalInfo.setRoom(roomNumber);
			window.location="/multigame"
		}
	};
	return (
		<div className={classs.room}>
			<label htmlFor="">{props.name}</label>
			<input onChange={(event) => setRN(event.target.value)}></input>
			<button className={classs.roomButton} onClick={clickHendler}>
				Submit!
			</button>
		</div>
	);
};
export default InputRoom;
