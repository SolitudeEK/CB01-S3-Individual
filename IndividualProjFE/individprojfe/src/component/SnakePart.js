import classes from "../styles/Snake.module.css";
const SnakePart = (props) => {
	var x = props.posX;
	var y = props.posY;
	var color= props.color
	const pos={
		top: y*25,
		left: x*25,
		backgroundColor: color
	};
	//console.log(x);
	//console.log(y);
	return <div style={pos} className={classes.snakePart}></div>;
};

export default SnakePart;
