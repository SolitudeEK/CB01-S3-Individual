import React from "react";
import classes from "../styles/Message.module.css";
var message = new Array();
const setTT = (text) => {
	for (var i = 0; i < text.length; i += 2) {
		message.push(
			<div className={classes.bodyText}>
				<h1 className={classes.sender}>{text[i]} :</h1>
				{text[i + 1]}{" "}
			</div>
		);
	}
};
class Messaging extends React.Component {
	constructor(props) {
		super(props);
		console.log("321");
	}
	componentDidMount() {
		setTT(this.props.text);
		this.interval = setInterval(
			() => this.setState({ time: Date.now() }),
			500
		);
	}
	componentWillUnmount() {
		clearInterval(this.interval);
	}

	render() {
		return message;
	}
}
export default Messaging;
