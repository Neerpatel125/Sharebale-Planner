import React, { useState }from "react";
import {Link} from 'react-router-dom';
import "./App.css"

function RegisterPage1 ()
{
    async function sendToPersons(person){
        await fetch("/persons", {
          method: "Post",
          headers: {
            "Accept": "application/json", 
            "Content-Type": "application/json"
          },
          body: JSON.stringify(person),
        });
    }
    async function fetchFromPersons(userName){
        const response = await fetch("/persons/userName/" + userName, {
          method: "Get",
          headers: {
            "Accept": "application/json", 
            "Content-Type": "application/json"
          },
        });
        const body = await response.json();
        return body; 
    }

    async function handleSubmitButton(){
        if (enteredEmail === "" || enteredUserName === "" || enteredPassword === ""){
            alert("Username, password, or email cannot be empty.");
            return;
        } 
        const person = {
            userName: enteredUserName, 
            password: enteredPassword, 
            email: enteredEmail
        };
        const gotPerson = await fetchFromPersons(enteredUserName); 
        if (gotPerson.length < 1){
            sendToPersons(person); 
            alert("Account Created!");
        }
        else{
            alert("Username already exists."); 
        }
    }

    const [enteredUserName, setEnteredUserName] = useState("");
    const [enteredPassword, setEnteredPassword] = useState(""); 
    const [enteredEmail, setEnteredEmail] = useState("");
    
    return (
        <div className ="login-page1">
            <div className ="row">
                <div className ="col-md-6 d-flex align-items-center justify-content-center">
                    <div className = "text-part d-flex flex-column">
                        <h1>REGISTER</h1>
                         {/* <label for="email">Email </label> */}
                            <input onChange={(e) => setEnteredEmail(e.target.value)} value={enteredEmail} type="email" placeholder='Email Adress' id="email2"/>
                            <div className="email2">  
                        {/* <label for="email">UserName </label> */}
                             <input onChange={(e) => setEnteredUserName(e.target.value)} value={enteredUserName} type="text" placeholder='User Name' id="userName2"/>
                          </div> <div className="userName2">
                        {/* <label for="email">Password </label> */}
                        <input onChange={(e) => setEnteredPassword(e.target.value)} value={enteredPassword} type="password" placeholder='Password' id="pwd2"/>
                        </div>
                        <button onClick={handleSubmitButton}>Submit</button>
                        <div className = "d-flex align-items-center justify-content-between">
                           
                            <p>Already a menber?  <Link to='/'>Login Here</Link></p>
                        </div>
                    </div>
                </div>
                <div className="col-md-6 d-flex align-items-center">
                    <img src="data:image/jpeg;base64,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2VvRS061RlmlV62Eo1jL2yeMkehVAr6p129m67kY9QFoVtDWhvaou7sfSV05FO3HVBqAfnJU37JonQkeR9R+VyNwgwcDuOCLq9ak1whzQ4biAfVaNfQVnf2qTe6W/wCmFMMe3e352VV2x3/xeD1BHpK5sVr1LHTObB4R6LoNXVGgeyXN7wR6T5rQq6mO+CqDzaR6EqZuLpO3x1H+1Wds3Et0E9CPzCotTRLNhI75HmrFq/qpVc0fAyZLjgXcm7cI4K3aK1eo0YcRff8AM4YDk3IeqnFBVxsWp+f9K3Q2QDev5D8n28Co3ROiKdnaQySTF5xJl0THAZnJSSIqDnFxlxkrs06bKbcrBA4BQOntANri8yG1d+w8Cq43VW0H4QObm+y6Cimp4qowQO6q1tn0Krszhfl/orntTUR7zLyyeZ9l6Z9Hu9zP4z6roCLb95V5eS0GzMPwPmVR2fR83a9n/jn1cs9PUOmP8Qd1MD3VxRa/uqvHsPZbjZ2G/wAe7vdVdupdH53dwAWduqFAbah5uHsFYUWv7mr/AJLcYLDj+AUC3VWy/ITzcfZZW6s2Uf4Xi95/mUyiwa1X/I+a2GEw4/8AW3/iPZadj0fSpT0bA2YmJxjLNbiIoySTJU7WhogCEREWFlERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERf/2Q==" alt="" />

                </div>
            </div>
           <div className="login-page1-rectangle">

           </div>

        </div>
    )
}

export default RegisterPage1
