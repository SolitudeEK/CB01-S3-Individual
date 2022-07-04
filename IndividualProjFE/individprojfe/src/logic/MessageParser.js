import Token from "../logic/Token.js";
class MessageParser {
	static parse(msg){
		if(msg.headers['access']!=null&&
			msg.headers['refresh']!=null)
		{
			Token.setTokens(msg.headers['access'], msg.headers['refresh']);
			console.log("success");
		}
		return JSON.parse(msg.body);
	}
}
export default MessageParser;