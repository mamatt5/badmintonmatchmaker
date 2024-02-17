import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import '../styles/AddPlayerPage.css'

// This file renders the Add Player functionality of the app. It allows the user to add a player into the database. It loads
// available brackets from the database to restrict allowed user inputs only from [A, B, C, D, E].

const AddPlayerPage = () => {
    const [errorMessage, setErrorMessage] = useState("")
    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [bracket, setBracket] = useState("")
    const [beemIt, setBeemIt] = useState("")

    const navigate = useNavigate();
    const[brackets, setBrackets] = useState([])

    useEffect(() => {loadBrackets()}, [])

    useEffect(() => {
        if (errorMessage) {
            const timer = setTimeout(() => {setErrorMessage("");}, 2000);
            return () => clearTimeout(timer)
        }
    }, [errorMessage])

    const loadBrackets = () => {
        axios.get('http://localhost:8088/badminton/brackets')
        .then(response => setBrackets(response.data))
    }

    const createPlayer = (event) => {
        event.preventDefault();

        // Removes any trailing whitespace from the input
        const trimFirstName = firstName.trim()
        const trimLastName = lastName.trim()

        if (trimFirstName != "" && trimLastName != "") {
            const player = { firstName: trimFirstName, lastName: trimLastName, bracket, beemIt }

            axios.post('http://localhost:8088/badminton/players', player)
            .then(response => navigate("/players"))
        
        } else {

            setErrorMessage("Name cannot be empty.")
        }
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

            {/* Lets the user choose only from available brackets stored in the database. The logic behind
            the ternary expression for value was to fix a bug where when the page was initially rendered
            and the user did not change the selection from the drop-down list, the form crashed. It also
            crashed when "Select bracket" was chosen again since it will not have a "category" property
            that is available from the database.*/}


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

        <p style={{color: 'red'}}>{errorMessage}</p>

    </form>
    </>
    )
}

export default AddPlayerPage