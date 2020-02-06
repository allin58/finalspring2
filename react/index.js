import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios'
class Hello extends React.Component {
    
    constructor(){
        super();
        this.state = {
            message: "my friend (from state)!",
            allpairs: [],
            activepair: []
                  };

  axios.get('http://localhost:8080/cryptopairservice/getallpairs')
    .then(response => {this.setState({ allpairs: response.data });
                      console.log(response.data)})   
        
        
         axios.get('http://localhost:8080/cryptopairservice/getactivepairs')
    .then(response => {this.setState({ activepair: response.data }) })   
        
        
   
        
        
        this.updateMessage = this.updateMessage.bind(this);
        this.getallpairs = this.getallpairs.bind(this);
        this.getactivepairs = this.getactivepairs.bind(this);           
        this.togglepair = this.togglepair.bind(this);           
    }
    
  
 
    
    
    updateMessage() {
        this.setState({
            message: "my friend (from changed state)!"
          
        });
        
     
        
    }
    


    getallpairs() {      
   axios.get('http://localhost:8080/cryptopairservice/getallpairs')
    .then(response => {this.setState({ allpairs: response.data }) })   
        
}
    
    
    
    
    
     getactivepairs() {
     axios.get('http://localhost:8080/cryptopairservice/getactivepairs')
    .then(response => {this.setState({ activepair: response.data }) })    }  
        
    
    
   
     togglepair(id)     {
     axios.get('http://localhost:8080/cryptopairservice/togglepair?id='+id)
    .then(response => { this.getallpairs()})   
        }   

    

    
    
    render() {
         return (
           <div>
         
       
          
     
        <table className="uui-table">
            <thead>
                <tr>
                    <th>index</th>
                    <th>pair</th>
                    <th>state</th>
                    <th>action</th>
                </tr>
            </thead>

          <tbody>
         
                 {this.state.allpairs.map(d => <tr key={d.identity}><th>{d.identity}</th><th>{d.pair}</th><th>{''+d.active}</th><th><button type="submit" className="uui-button test-color dark-gray" onClick={ () => this.togglepair(d.identity)}         >toggle</button></th></tr> )}     
                         
          
          </tbody>
          
        </table>        
   

             
           </div>   
        )
    }
}

ReactDOM.render(<Hello/>, document.getElementById("root"));





/*

 <h1>Hello {this.state.message}!</h1>
            <button onClick={this.updateMessage}>updateMessage</button>
           <button onClick={this.getallpairs}>getallpairs</button>
         <button onClick={this.getactivepairs}>getactivepairs</button>
         
         
           <ul>
        {this.state.allpairs.map(d => <li key={d.identity}>{d.identity} {d.pair} {''+d.active}    <button type="submit" className="uui-button test-color dark-gray" onClick={ () => this.togglepair(d.identity)}         >toggle</button> </li> )}
      </ul>
             
         
         
         
         */

