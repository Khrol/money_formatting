import React from 'react';
import Result from '../Result/Result'

export class FormattingForm extends React.Component {
    state = {
        input: '',
        text: '',
        success: true
    }

    changeDataHandler = (event) => {
        const newValue = event.target.value
        if (/^[-0-9.]*$/.test(newValue)) {
            this.setState({input: newValue})
        }
    };

    clickDataHandler = () => {
        fetch('http://localhost:8080/format?amount=' + this.state.input)
        .then((response) => {
            this.setState({success: response.ok});
            return response.text()
        }).then((text) => {
            this.setState({text: text})
        })
        .catch((reason) => {
            this.setState({
                success: false,
                text: 'Something went wrong: ' + reason
            })
        })
    }

    render() {
        return <div>
            <input type="text" value={this.state.input} onChange={this.changeDataHandler}></input>
            <Result success={this.state.success}>{this.state.text}</Result>
            <button onClick={this.clickDataHandler}>Format</button>
        </div>
    }
}

export default FormattingForm;
