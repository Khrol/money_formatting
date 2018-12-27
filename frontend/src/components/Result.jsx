import React from 'react';

const result = (props) => {
    let style = {color: 'green'};
    if (!props.success) {
        style = {color: 'red'};
    }
    return <p style={style}>{props.text}</p>
}

export default result;
