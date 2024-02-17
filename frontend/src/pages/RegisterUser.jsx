import axios from 'axios'
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

// This file renders the register page for registering users. Contents should be relatively straightforward as it 
// has the usual components for a typical login page. Errors are caught and displayed on the page. Users are expected
// to have at least 6 characters for both username and password. There are no restrictions on whatever the user
// inputs as their username or password.

const RegisterUser = () => {
    const [errorMessage, setErrorMessage] = useState("")
    const navigate = useNavigate()
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [confirmPassword, setConfirmPassword] = useState("")
    const [showPassword, setShowPassword] = useState(false)

    const registerUser = (event) => {
        event.preventDefault()

        // Checks for any whitespace in both fields
        const validUsername = /\s/g.test(username)
        const validPassword = /\s/g.test(password)

        if (!validUsername && !validPassword) {
            if (confirmPassword === password){
                const user = { username, password }
                axios.post('http://localhost:8088/badminton/users', user)
                .then(response => navigate("/login"))
                .catch(error => setErrorMessage("Username already exists"))

            } else {
                setErrorMessage("Passwords do not match.")
            }
            
        } else {
            setErrorMessage("Username/password cannot have spaces.")
        }
    }

  return (
    <>
    <h1>Register user</h1>
    <form className='loginForm' onSubmit={registerUser}>

        <div>
            <p>Username</p>
            <input required 
            type="text" 
            value={username} 
            minLength={6} 
            className="loginInput"
                onChange={(e) => setUsername(e.target.value)} />
        </div>
            
        <div>
            <p>Password</p>
            <input required 
                type={showPassword ? "text" : "password"} 
                value={password} 
                minLength={6}
                className="loginInput"
                onChange={(e) => setPassword(e.target.value)} />

            <button 
            type="button" 
            onMouseDown={() => setShowPassword(true)} 
            onMouseUp={() => setShowPassword(false)} 
            onMouseLeave={() => setShowPassword(false)}
            style={{backgroundColor: 'transparent', padding: '0px', marginLeft: '10px', border: 'none', outline: 'none'}}>
                    {showPassword ? "x" : "o"}</button>
        </div>

        <div>
            <p>Confirm password</p>
            <input required 
            type={showPassword ? "text" : "password"} 
            value={confirmPassword} 
            className="loginInput"
            onChange={(e) => setConfirmPassword(e.target.value)} />

            <button 
            type="button" 
            onMouseDown={() => setShowPassword(true)} 
            onMouseUp={() => setShowPassword(false)} 
            onMouseLeave={() => setShowPassword(false)}
            style={{backgroundColor: 'transparent', padding: '0px', marginLeft: '10px', border: 'none', outline: 'none'}}>
                    {showPassword ? "x" : "o"}</button>
        </div>

        <div style={{color: 'red'}}>{errorMessage}</div>

        <button type="submit" className="loginButton">Register</button>

    </form>

    <button style={{backgroundColor: 'gray'}} className="loginButton"
            onClick={()=>navigate("/login")}>
                
                Back</button>
    </>
  )
}

export default RegisterUser