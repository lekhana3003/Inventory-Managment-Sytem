import React from 'react';
import "./form.css"

 class Formtype extends React.Component
 {
 	constructor(props)
 	{
 		super()
 		this.state={
 			type:props.type,
 			invName:"",
 			invDesc:"",
 			itemName:"",
 			itemQty:""
 		}
 		this.onSubmit=this.onSubmit.bind(this)
 	}
 	onSubmit(e)
 	{
 		e.preventDefault()
 		console.log(this.state.invName)
 		var json='{ "invName":"'+this.state.invName+'", "invDesc":"'+this.state.invDesc+'"}';
 		var jsonData=JSON.parse(json);
 		console.log(jsonData)
 		fetch('http://localhost:8080/addInventory', {
   			 method: 'POST',
    			body: json,
    			accept: 'application/json'
				})
 		.then(response => response.json())
    	.then(data => {this.props.handleClick(data)})
    	.then(window.location.reload(false))

    		this.setState({
    			invDesc:"",
    			invName:""
    		})
 	}

 	render()
 	{
 		if(this.state.type==="inventory")
 		{
 			return(
 				<form>
 				<label htmlFor="invName">Inventory name:</label> 
 				<br/>
 				<input type="text" 
 				value={this.state.invName}
 				onChange={e=> this.setState({invName:e.target.value})} />
 				<br/>
 				<label htmlFor="invDesc">Inventory Description:</label> 
 				<br/>
 				<input type="text"  
 				value={this.state.invDesc}
 				onChange={e=> this.setState({invDesc:e.target.value})}/>
 				<br/>
 				 <button onClick={e => this.onSubmit(e)}>Submit</button>
 				</form>
 				)


 		}
 		else
 		{
 			return(
 				<form>
 				<label htmlFor="itemName">Item name:</label> 
 				<br/>
 				<input type="text" 
 				value={this.state.itemName}
 				onChange={e=> this.setState({itemName:e.target.value})} />
 				<br/>
 				<label htmlFor="invDesc">Item Quantity:</label> 
 				<br/>
 				<input type="text"  
 				value={this.state.itemQty}
 				onChange={e=> this.setState({itemQty:e.target.value})}/>
 				<br/>
 				<button onClick={e => this.onSubmit(e)}>Submit</button>
 				
 				</form>
 				)
 		}
 	}
 }
 export default Formtype