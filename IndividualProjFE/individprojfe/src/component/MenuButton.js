import classes from "../styles/MenuButton.module.css";
import { Link } from "react-router-dom";
import Token from "../logic/Token.js";
const MenuButton = (props) => {
	const exitHandler = () => {
		if (props.link === "/") {

			Token.clear();
		}
	};
	return (
		<div className={classes.box}>
			<Link
				onClick={exitHandler}
				className={classes.btnLink}
				to={props.link}
			>
				{props.title}
			</Link>
		</div>
	);
};
export default MenuButton;
