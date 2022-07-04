class Token {
	static set(token, refresh, login) {
		localStorage.setItem("token", token);
		localStorage.setItem("refresh", refresh);
		localStorage.setItem("login", login);
	}
	static setTokens(token, refresh) {
		localStorage.setItem("token", token);
		localStorage.setItem("refresh", refresh);
	}
	static setLogin(login) {
		localStorage.setItem("login", login);
	}
	static getToken() {
		return localStorage.getItem("token");
	}
	static getLogin() {
		return localStorage.getItem("login");
	}
	static getRefresh() {
		return localStorage.getItem("refresh");
	}
	static isSet() {
		if (localStorage.getItem("token") == null) return false;
		return true;
	}
	static clear() {
		localStorage.clear();
	}
	static getHeader() {
		return {
			token: localStorage.getItem("token"),
			refresh: localStorage.getItem("refresh"),
		};
	}
}
export default Token;
