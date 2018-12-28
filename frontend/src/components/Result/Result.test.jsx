import React from 'react';
import Result from './Result';
import { configure, shallow } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

configure({adapter: new Adapter()});

describe('<Result />', () => {
    it('renders red text', () => {
        const component = shallow(
            <Result success={false} >Red text</Result>
        );
        expect(component.html()).toEqual('<p id="result" style="color:red">Red text</p>')
    });
    
    it('renders green text', () => {
        const component = shallow(
            <Result success={true} >Green text</Result>
        );
        expect(component.html()).toEqual('<p id="result" style="color:green">Green text</p>')    
    });    
});
