import classes from "../styles/BackSign.module.css";
const BackSign = () => {
	const backHandler = () =>{
		window.location = "/menu";
		
	}
	return <div className={classes.back} onClick={backHandler}>
		back
	</div>
};
export default BackSign;