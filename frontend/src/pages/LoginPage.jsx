import React, { useState } from 'react';
import '../styles/LoginPage.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const LoginPage = (props) => {
    const [bearer, setBearer] = props.bearer
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [errorMsg, setErrorMsg] = useState("")
    const navigate = useNavigate()

    const handleLogin = (event) => {
        event.preventDefault();

        axios.post('http://localhost:8088/badminton/auth/login', {}, {
            auth:{
                username: username,
                password: password
            }
        })
        .then(response => {setBearer("Bearer " + response.data);navigate("/admin/dashboard")})
        .catch(()=> setErrorMsg("Login error"))
    };

    return (
        <>
            <form onSubmit={handleLogin} className="loginForm">
            <h1>Badminton Matchmaker</h1>
                <div>
                    <p>Username</p>
                    <input 
                        type="text" 
                        required 
                        value={username} 
                        onChange={(e) => setUsername(e.target.value)} 
                        className="loginInput" 
                    />
                </div>

                <div>
                    <p>Password</p>
                    <input 
                        type="text" 
                        required 
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                        className="loginInput" 
                    />
                </div>
                {errorMsg && <p className="errorMsg">{errorMsg}</p>}
                <button type="submit" className="loginButton">Login</button>
            </form>
        </>
    );
};

export default LoginPage;
