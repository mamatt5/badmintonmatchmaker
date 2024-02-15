import axios from 'axios'
import React from 'react'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

const RegisterUser = () => {
    const [errorMessage, setErrorMessage] = useState("")
    const navigate = useNavigate()
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const registerUser = (event) => {
        event.preventDefault()
        const validUsername = /\s/g.test(username)
        const validPassword = /\s/g.test(password)

        if (!validUsername && !validPassword) {
            const user = { username, password }
            axios.post('http://localhost:8088/badminton/users', user)
            .then(response => navigate("/login"))
            .catch(error => setErrorMessage("Username already exists"))
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
            <input required type="text" value={username} className="loginInput"
                onChange={(e) => setUsername(e.target.value)} />
        </div>
            <p>Password</p>
            <input required type="text" value={password} className="loginInput"
                onChange={(e) => setPassword(e.target.value)} />
        <div>

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