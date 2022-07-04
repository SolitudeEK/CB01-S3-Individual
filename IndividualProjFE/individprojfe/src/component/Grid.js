import classes from "../styles/Grid.module.css";
import Snake from "../component/Snake.js";
import Fruit from "../component/Fruit.js";
import Points from "../component/Points.js";
import Token from "../logic/Token.js";
import MessageParser from "../logic/MessageParser.js";
import React from "react";
import BackSign from "../component/BackSign.js";

var stompClient = null;
var data = null;
var lastKeyPressed = 0;
var mn = {
	points: 0,
	snakePos: [0, 0, 0, 1],
	fruitPos: [0, 0],
};
const keyDownHandle = (e) => {
	if (e.key === "a") lastKeyPressed = -1;
	else if (e.key === "d") lastKeyPressed = 1;
	else if (e.key === "w") lastKeyPressed = 0;
	console.log(lastKeyPressed);
};
const startHandler = () => {
	stompClient.send(
		"/app/sologame/start",
		Token.getHeader(),
		JSON.stringify(Token.getLogin())
	);
};
const exitHandler = () => {
	stompClient.send(
		"/app/sologame/end",
		Token.getHeader(),
		JSON.stringify(Token.getLogin())
	);
};
const turn = () => {
	const message = {
		login: Token.getLogin(),
		turn: lastKeyPressed,
	};
	stompClient.send(
		"/app/sologame/turn",
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
		"/user/sologame/" + Token.getLogin() + "/render",
		onMessageReceived
	);
};
const onMessageReceived = (msg) => {
	data = MessageParser.parse(msg);
	console.log(data["isPlaying"]);
	if (data["isPlaying"]) {
		mn = data;
		setTimeout(() => turn(), 500);
	} else {
		alert("game ended! your points is " + data["points"]);
		window.location = "/menu";
	}
};
const onError = (err) => {
	console.log("err: " + err);
};
const begin = () => {
	setTimeout(() => startHandler(), 200);
	connect();
};
class Grid extends React.Component {
	constructor(props) {
		super(props);
		begin();
	}
	componentDidMount() {
		this.interval = setInterval(
			() => this.setState({ time: Date.now() }),
			500
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
				<BackSign onClick={exitHandler} />
				<Points points={mn.points} />
				<div className={classes.grid}>
					<Fruit pos={mn.fruitPos} />
					<Snake pos={mn.snakePos} />
				</div>
			</div>
		);
	}
}

export default Grid;
