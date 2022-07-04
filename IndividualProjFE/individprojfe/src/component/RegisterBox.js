import classes from "../styles/LoginBox.module.css";
import React, { useState } from "react";
const RegisterBox = () => {
	var stompClient = null;
	var sha256 = require('js-sha256').sha256;
	const [login, setLogin] = useState("");
	const [password, setPassword] = useState("");
	const [passwordCheck, setPasswordCheck] = useState("");

	const registerHandler = (event) => {
		if(password===passwordCheck)
		{
		connect();
		const message = {
			login: login,
			password: sha256(password),
		};
		setTimeout(() => {
			stompClient.send("/app/registration", {token: "Credentials"}, JSON.stringify(message));
		}, 500);
	}
	else 
		console.log("Not Correct!");
		event.preventDefault();
	};
	const connect = () => {
		var SockJS = require("sockjs-client/dist/sockjs.js");
		const Stomp = require("stompjs");
		SockJS = new SockJS("http://localhost:8080/ws");
		stompClient = Stomp.over(SockJS);
		stompClient.connect({}, onConnected, onError);
	};

	const onConnected = () => {
		stompClient.subscribe("/user/"+login+"/result", onMessageReceived);
	};
	const onMessageReceived = (msg) => {
		if (JSON.parse(msg.body) === true) {
			window.location = "/";
		}
		else
		{
			console.log("Already used!");
		}
	};

	const onError = (err) => {
		console.log("err: " + err);
	};
	return (
		<div className={classes.box}>
			<label htmlFor="">Login</label>
			<input onChange={(event) => setLogin(event.target.value)}></input>
			<label htmlFor="">Password</label>
			<input
				onChange={(event) => setPassword(event.target.value)}
			></input>
			<label htmlFor="">Repeat Password</label>
			<input
				onChange={(event) => setPasswordCheck(event.target.value)}
			></input>
			<button onClick={registerHandler}>enter</button>
		</div>
	);
};

export default RegisterBox;
