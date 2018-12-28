import React from 'react';

const result = (props) => {
    let style = {color: 'green'};
    if (!props.success) {
        style = {color: 'red'};
    }
    return <p id='result' style={style}>{props.children}</p>
}

export default result;
