import React from 'react';
import ApiButton from './ApiButton' 
import ReactDOM from "react-dom";

export default class Table extends React.Component {
    
    constructor(props){
    var newItems=[]
      super(props);

           this.id = React.createRef();
            this.getHeader = this.getHeader.bind(this);
            this.editRow=this.editRow.bind(this)
            this.getKeys = this.getKeys.bind(this);
            this.getRowsData = this.getRowsData.bind(this);
            this.getInventoryValues= this.getInventoryValues.bind(this);
            this.state={
              deleteRow:null
            }
    }
    

getKeys = function(){
  var   header=[];
if (this.props.type==="inventory")
 {
     header=["InvId","InvName","InvDescription","Edit","Delete"];
  }
  else
  {
    header=["ItemId","ItemName","Qty","Edit","Delete"];
  }
  return header;  
}

editRow= function()
{

  var items=this.props.data;
//console.log(this.state.deleteRow)
  var newItems= items.map((row)=>{
row['edit']="";
row['delete']="";
return row
  })
 if(this.props.appendData!==null)
 {
  console.log(this.props.appendData)
  var appendDataNew=this.props.appendData;
  appendDataNew["edit"]="";
  appendDataNew["delete"]="";
  //console.log(appendDataNew)
  newItems.push(appendDataNew)

 }
 console.log(newItems)
  return newItems;
}

 getHeader = function(){
        var keys = this.getKeys();

        return keys.map((key, index)=>{
          return <th key={key}>{key.toUpperCase()}</th>
        })
      }

getInventoryValues = function(index,type){
  if(index)
        {
          if(type==="delete")
          {
            fetch('http://localhost:8080/deleteInventory/'+index)
            .then(window.location.reload(false))
      
          }
          else
          {
            fetch('http://localhost:8080/editInventory/'+index)
            .then(window.location.reload(false))
          }
          
     }
     
 
      }

getRowsData = function(){ 
      var items = this.editRow();
       var i=0;
      var keys = Object.keys(items[0]);
      
      return items.map((row, index)=>{
        return <tr key={index}><RenderRow key={index} row={row} keys={keys} index={row['invId']} thispointer={this}/></tr>
      })
    }
   

render() {
   
        return (
          <div>
            <table>
            <thead>
              <tr>{this.getHeader()}</tr>
            </thead>
            <tbody>
              {this.getRowsData()}
            </tbody>
            </table>
          </div>
          
        );
    }
}
const RenderRow = (props) =>{
 
  return props.keys.map((key, index)=>{
   console.log(props.index)
    if (key==="edit") {
      return <td key={index}><button id={props.index} onClick={() => props.thispointer.getInventoryValues(props.index,"edit")}>Edit</button></td>
    }
     if (key==="delete") {
      return <td key={index}><button id={props.index} onClick={() => props.thispointer.getInventoryValues(props.index,"delete")}>Delete</button></td>
    }
    return <td key={index}>{props.row[key]}</td>
  })
}
