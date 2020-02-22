import React from 'react';
class ApiData extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      methodType:this.props.methodType,
      url:this.props.url,
      body:this.props.body,
      items: []

    };
  }

  sendApiData() {
    if(this.state.body==="null"){
    fetch(this.state.url,{
        method: this.state.methodType,
        headers: {
            'Content-Type': 'application/json'
        }
    })
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            items: result.items
          });
        },
        
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }
  else
  {
    fetch(this.state.url,{
        method: this.state.methodType,
        body:JSON.stringify(this.props.body),
        headers: {
            'Content-Type': 'application/json'
        }
    })
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            items: result.items
          });
        },
        
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }
}

  render() {
    const { error, isLoaded, items } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      console.log(items[0])

      return (
        <ul>
          {items.map(item => (
            <li key={item.invId}>
              {item.invName} {item.invDesc}
            </li>
          ))}
        </ul>
      );
    }
  }
}
export default ApiData;