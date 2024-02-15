import axios from 'axios'
import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import '../styles/AddPlayerPage.css'

const AddPlayerPage = () => {
    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [bracket, setBracket] = useState("")
    const [beemIt, setBeemIt] = useState("")

    const navigate = useNavigate();
    const[brackets, setBrackets] = useState([])

    useEffect(() => {loadBrackets()}, [])

    const loadBrackets = () => {
        axios.get('http://localhost:8088/badminton/brackets')
        .then(response => setBrackets(response.data))
    }

    const createPlayer = (event) => {
        event.preventDefault();
        const trimFirstName = firstName.trim()
        const trimLastName = lastName.trim()
        const player = { firstName: trimFirstName, lastName: trimLastName, bracket, beemIt }

        axios.post('http://localhost:8088/badminton/players', player)
        .then(response => navigate("/players"))
    }

    return (
    <>
    <h1>Create new player</h1>
    <form onSubmit={createPlayer} className='AddPlayerForm'>
        <div>
            <label>First name: </label>
            <input required value={firstName} onChange={(e) => setFirstName(e.target.value)} />
        </div>

        <div>
            <label>Last name: </label>
            <input required value={lastName} onChange={(e) => setLastName(e.target.value)} />
        </div>

        <div>
            <label>BeemIt: </label>
            <input value={beemIt} onChange={(e) => setBeemIt(e.target.value)} />
        </div>

        <div>
            <label>Bracket: </label>
            <select required value={bracket ? bracket.category : ""} onChange={(e) => 
                setBracket(brackets.find(bracket => bracket.category === e.target.value))}>
                            <option disabled value="">Select bracket</option>
                        {brackets.map(bracket => (
                            <option key={bracket.id} value={bracket.category}>{bracket.category}</option>
                        ))}
            </select>
        </div>

        <div>
        <button type='submit' style={{backgroundColor: 'green'}}>Submit</button>
        <button style={{backgroundColor: 'gray'}} onClick={() => navigate('/players')}>Cancel</button>
        </div>
    </form>
    </>
    )
}

export default AddPlayerPage