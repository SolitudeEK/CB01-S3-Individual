import classes from "../styles/LoginBox.module.css";
import React, { useState, useEffect } from "react";
import Token from "../logic/Token.js";
import MessageParser from "../logic/MessageParser.js";
const LoginBox = () => {
    var stompClient = null;
    var sha256 = require("js-sha256").sha256;
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    const registerationHandler = () => {
        window.location = "/register";
    };
    const loginAunt = () => {
        connect();
        setTimeout(() => {
            stompClient.send(
                "/app/authentication",
                Token.getHeader(),
                JSON.stringify(Token.getLogin())
            );
        }, 400);
    };

    const loginHandler = (event) => {
        const message = {
            login: login,
            password: sha256(password),
        };
        connect();
        setTimeout(() => {
            stompClient.send(
                "/app/authorization",
                { token: "Credentials" },
                JSON.stringify(message)
            );
        }, 400);
        event.preventDefault();
    };
    const connect = () => {
        var SockJS = require("sockjs-client/dist/sockjs.js");
        const Stomp = require("stompjs");
        SockJS = new SockJS("http://localhost:8080/ws");
        stompClient = Stomp.over(SockJS);
        stompClient.connect({}, onConnected, onError);
    };
    const onConnected = () => {
        if (login !== "") {
            stompClient.subscribe(
                "/user/" + login + "/result",
                onMessageReceived
            );
        }
        stompClient.subscribe(
            "/user/" + Token.getLogin() + "/result",
            onMessageReceived
        );
    };
    const onMessageReceived = (msg) => {
        if (MessageParser.parse(msg) === "false") {
            console.log("incorrect details");
            Token.clear();
        } else {
            Token.setLogin(login);
            window.location = "/menu";
        }
    }
    const onError = (err) => {
        console.log("err: " + err);
    };
    useEffect(() => {
        if (Token.isSet()) {
            loginAunt();
        }
    }, []);
    return (
        <div className={classes.box}>
            <label htmlFor="">Login</label>
            <input onChange={(event) => setLogin(event.target.value)}></input>
            <label htmlFor="">Password</label>
            <input
                onChange={(event) => setPassword(event.target.value)}
            ></input>
            <button onClick={loginHandler}>enter</button>
            <div className={classes.linkReg} onClick={registerationHandler}>
                Do not register yet?
            </div>
        </div>
    );
};

export default LoginBox;
