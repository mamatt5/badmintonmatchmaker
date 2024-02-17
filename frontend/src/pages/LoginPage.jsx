import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/LoginPage.css';

// This file renders the Login page or serves as the landing page of the whole application. Contents should be
// relatively straightforward as it has the usual components for a typical login page. Errors are caught and
// displayed on the page (incorrect username/password combination). It should not check if the username exists
// as it would pose as a security risk in the future.

const LoginPage = (props) => {
    const [bearer, setBearer] = props.bearer
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [showPassword, setShowPassword] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
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
        .catch(()=> setErrorMessage("Username/password is incorrect."))
    };

    useEffect(() => {
        if (errorMessage) {

            const timer = setTimeout(() => {setErrorMessage("");}, 2000);
            return () => clearTimeout(timer)

        }
    }, [errorMessage])

    return (
        <>
            <form onSubmit={handleLogin} className="loginForm">

            <h1>Badminton Matchmaker</h1>

                <div>
                    <p>Username</p>
                    <input type="text" required value={username} onChange={(e) => setUsername(e.target.value)} className="loginInput" />
                </div>

                <div>
                    <p>Password</p>
                    <input type={showPassword ? "text" : "password"} required value={password} onChange={(e) => setPassword(e.target.value)} className="loginInput" />

                    <button 
                    type="button" 
                    onMouseDown={() => setShowPassword(true)} 
                    onMouseUp={() => setShowPassword(false)} 
                    onMouseLeave={() => setShowPassword(false)}
                    style={{backgroundColor: 'transparent', padding: '0px', marginLeft: '10px', border: 'none', outline: 'none'}}>
                            {showPassword ? "x" : "o"}
                    </button>
                </div>

                <div style={{color: 'red'}}>{errorMessage}</div>

                <button type="submit" className="loginButton">Login</button>

            </form>
            <Link to="/register">Register account</Link>
        </>
    );
};

export default LoginPage;
