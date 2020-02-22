import React from 'react';
 function Api(props)
{
 return((props)=>{
    const items=[];
        if(props.body==="null"){
    fetch(props.url,{
        method: props.methodType,
        headers: {
            'Content-Type': 'application/json'
        }
    })
      .then(res => res.json())
      .then(
        (result) => {items=result.items},
      )
      return(items)
  }
  else
  {
    fetch(props.url,{
        method: props.methodType,
        body:JSON.stringify(props.body),
        headers: {
            'Content-Type': 'application/json'
        }
    })
      .then(res => res.json())
      .then(
        (result) => {items=result.items},
      )
      return(items)
        }
      
  }
  )
}

export default Api