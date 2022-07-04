import SnakePart from "../component/SnakePart.js";
const Snake = (props) => {
	var snakePoses = props.pos;
	var snakepart = new Array();
	const setTT = () => {
		for (var i = 0; i < snakePoses.length; i += 2) {
			snakepart.push(<SnakePart color={props.color} posX={snakePoses[i+1]} posY={snakePoses[i]} />);
		}
	};
	setTT();
	return snakepart;
};

export default Snake;
