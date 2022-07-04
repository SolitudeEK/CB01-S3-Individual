import MenuButton from "../component/MenuButton.js";
import classes from "../styles/MenuPage.module.css";
import Chat from "../component/Chat.js";
const MenuPage = () => {
	return (
		<div className={classes.boxPos}>
			<div className={classes.box}>
				<MenuButton link="/sologame" title="solo game" />
				<MenuButton link="/startroom" title="multiplayer" />
				<MenuButton link="/" title="exit" />
			</div>
		</div>
	);
};
//			 <div className={classes.chat}> <Chat /></div>
export default MenuPage;
