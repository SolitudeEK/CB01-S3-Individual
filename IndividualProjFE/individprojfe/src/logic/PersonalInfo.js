class PersonalInfo {
	static setRoom(room)
	{
		localStorage.setItem("room", room);
	}
	static getRoom()
	{
		return localStorage.getItem("room");
	}
	static deleteRoom()
	{
		localStorage.setItem("room", "");
	}
}
export default PersonalInfo;