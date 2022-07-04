import classes from "../styles/Points.module.css";
import React from "react";
const Points = (props) => {
	var points=props.points;
	if(points==="0"||points==="1")
		points="point";
	else
		points="points";
	return (
		<div className={classes.pointsPos}>
			<div className={classes.points}>{props.points} {points}</div>
		</div>
	);
};

export default Points;
