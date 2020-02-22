import React from 'react';
import Table from './Table';
import Form from './form'

 class App extends React.Component {

    constructor(props){
      super(props);
      this.state={
       tableData2:'default',
       appendData:null
      }
      this.getData=this.getData.bind(this);
    }
   componentDidMount()
   {
   
    fetch("http://127.0.0.1:8080/inventory")
    .then(response => response.json())
     
    .then(data => {
      this.setState({
        tableData2:data
      },() =>{console.log(typeof(data))})
      })
    
    //console.log(this.state)
    }
    
    getData = function(rowData) {
// This is the row data from ChildComponent
  this.setState({
    appendData:rowData
  })
  console.log(this.state)
  }
    render(){
        
        if(this.state.tableData2!=='default')
        {
          //console.log(this.state.tableData2)
        return (
          <div>
            <Form type="inventory" handleClick={this.getData}/> 
            <Table data={this.state.tableData2.items} type="inventory" appendData={this.state.appendData}/>
          </div>  
        );
      }
      else
      {
        return(<div>Loading</div>);
      }
    }
    
   
}
export default App;