import React, { Component } from 'react';

class ApiButton extends Component {
  render() {
    
    
    
    return (
      <button type="button" onClick={this.props.onClick(this.props.index)} id={this.props.index} >{this.props.buttonName}</button>
    )
  }
}
export default ApiButton;