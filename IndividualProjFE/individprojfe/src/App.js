import { Route, Switch } from "react-router-dom";
import LoginPage from "./pages/LoginPage.js";
import MenuPage from "./pages/MenuPage.js";
import RegisterPage from "./pages/RegisterPage.js";
import SoloGamePage from "./pages/SoloGamePage.js";
import RoomStartPage from "./pages/RoomStartPage.js";
import MultiGamePage from "./pages/MultiGamePage.js";
const App = () => {
	return (
		<div>
			<Switch>
				<Route path="/" exact>
					<LoginPage />
				</Route>
				<Route path="/register" exact>
					<RegisterPage />
				</Route>
				<Route path="/sologame" exact>
					<SoloGamePage />
					</Route>
				<Route path="/multigame" exact>
				<MultiGamePage />
				</Route>
				<Route path="/startroom" exact>
					<RoomStartPage />
				</Route>
				<Route path="/menu" exact>
					<MenuPage />
				</Route>
			</Switch>
		</div>
	);
};

export default App;
