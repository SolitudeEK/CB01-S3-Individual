import classes from "../styles/Grid.module.css";
import Snake from "../component/Snake.js";
import Fruit from "../component/Fruit.js";
import Token from "../logic/Token.js";
import PersonalInfo from "../logic/PersonalInfo.js";
import MessageParser from "../logic/MessageParser.js";
import React from "react";
import BackSign from "../component/BackSign.js";

var stompClient = null;
var lastKeyPressed = 0;
var mn = {
	snakeGreenPos: [1, 1, 1, 2],
	snakeRedPos: [28, 29, 29, 29],
	fruitPos: [0, 0]
};
const keyDownHandle = (e) => {
	if (e.key === "a") lastKeyPressed = -1;
	else if (e.key === "d") lastKeyPressed = 1;
	else if (e.key === "w") lastKeyPressed = 0;
	console.log(lastKeyPressed);
};
/*
const exitHandler = () => {
	stompClient.send(
		"/app/sologame/end",
		Token.getHeader(),
		JSON.stringify(Token.getLogin())
	);
};
*/
const turn = () => {
	const message = {
		room: PersonalInfo.getRoom(),
		login: Token.getLogin(),
		turn: lastKeyPressed,
	};
	stompClient.send(
		"/app/mutiplayer/turn/",
		Token.getHeader(),
		JSON.stringify(message)
	);
	lastKeyPressed = 0;
};
const connect = () => {
	var SockJS = require("sockjs-client/dist/sockjs.js");
	const Stomp = require("stompjs");
	SockJS = new SockJS("http://localhost:8080/ws");
	stompClient = Stomp.over(SockJS);
	stompClient.connect({}, onConnected, onError);
};
const onConnected = () => {
	stompClient.subscribe(
		"/user/room/" + PersonalInfo.getRoom() + "/thick",
		onMessageReceived
	);
	stompClient.subscribe(
		"/user/room/" + PersonalInfo.getRoom() + "/render",
		onMessageReceivedRender
	);
};
const onMessageReceived = (msg) => {
	turn();
};
const onMessageReceivedRender = (msg) => {
	mn = MessageParser.parse(msg);
};
const onError = (err) => {
	console.log("err: " + err);
};
class MultiGrid extends React.Component {
	constructor(props) {
		super(props);
		connect();
	}
	componentDidMount() {
		this.interval = setInterval(
			() => this.setState({ time: Date.now() }),
			250
		);
	}
	componentWillUnmount() {
		clearInterval(this.interval);
	}
	render() {
		return (
			<div
				className={classes.gridPos}
				onKeyPress={keyDownHandle}
				tabIndex="0"
			>
				<BackSign />
				<div className={classes.grid}>
					<Snake color={"red"} pos={mn.snakeRedPos} />
					<Fruit pos={mn.fruitPos} />
					<Snake pos={mn.snakeGreenPos} />
				</div>
			</div>
		);
	}
}

export default MultiGrid;
