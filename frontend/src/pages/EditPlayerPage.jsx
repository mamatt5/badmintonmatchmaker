import axios from 'axios'
import React from 'react'
import { useState } from 'react'
import { useEffect } from 'react'
import { useParams } from 'react-router-dom'
import '../styles/EditPlayerForm.css'
import { useNavigate } from 'react-router-dom'

const EditPlayerPage = () => {
    const {playerid} = useParams()
    const id = playerid
    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [bracket, setBracket] = useState("")
    const [beemIt, setBeemIt] =useState("")

    const [brackets, setBrackets] = useState([])
    const navigate=useNavigate();


    useEffect(() => {loadPlayer();loadBrackets()}, [])

    const loadBrackets = () => {
        axios.get('http://localhost:8088/badminton/brackets')
        .then(response => {setBrackets(response.data)})
    }

    const loadPlayer = () => {
        axios.get(`http://localhost:8088/badminton/players/${playerid}`)
        .then(response => {
            const { firstName, lastName, beemIt, bracket } = response.data;
            setFirstName(firstName);
            setLastName(lastName);
            setBeemIt(beemIt);
            setBracket(bracket)})
    }

    const editPlayer = (event) => {
        event.preventDefault()
        const trimFirstName = firstName.trim()
        const trimLastName = lastName.trim()
        const player = { id, firstName: trimFirstName, lastName: trimLastName, bracket, beemIt }

        axios.put('http://localhost:8088/badminton/players', player)
        .then(response => navigate("/players"))
    }

  return (
    <>
    <h2>Editing {firstName}'s info</h2>
        <form className="EditPlayerForm" onSubmit={editPlayer}>
        <div>
            <label>First name: </label>
            <input required value={firstName} onChange={(e) => setFirstName((e.target.value))} />
        </div>

        <div>
            <label>Last name: </label>
            <input required value={lastName} onChange={(e) => setLastName((e.target.value))} />
        </div>

        <div>
            <label>BeemIt: </label>
            <input value={beemIt} onChange={(e) => setBeemIt(e.target.value)} />
        </div>

        <div>
            <label>Bracket: </label>
            <select required value={bracket.category} onChange={(e) => 
                setBracket(brackets.find(bracket => bracket.category === e.target.value))}>
                        <option disabled>Select Bracket</option>
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

export default EditPlayerPage