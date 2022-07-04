import InputRoom from "../component/InputRoom.js";
import classes from "../styles/RoomStart.module.css";
const RoomStartPage = () => {
	return (
		<div className={classes.rsPos}>
			<InputRoom toSend="create" name="create room (Red Snake)" />
			<div className={classes.orPos}>-OR-</div>
			<InputRoom toSend="enter" name="join room (Green Snake)" />
		</div>
	);
};
export default RoomStartPage;
