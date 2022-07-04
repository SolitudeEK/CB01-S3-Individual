import classes from "../styles/Fruit.module.css";
const Fruit = (props) => {
	var cord=props.pos;
	var x = cord[1];//props.posX;
	var y = cord[0];//props.posY;
	const pos={
		top: y*25,
		left: x*25
	};
	//console.log(x);
	//console.log(y);
	return <div style={pos} className={classes.fruit}></div>;
};
export default Fruit;