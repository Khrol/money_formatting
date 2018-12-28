import React from 'react';
import FormattingForm from './FormattingForm';
import { configure, mount } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

configure({adapter: new Adapter()});

describe('<FormattingForm />', () => {
    const sample = (promiseF, expectedColor, expectedText) => () => {
        const p = new Promise(promiseF);

        const mock = jest.fn().mockImplementation(() => {
            return p;
        });
        global.fetch = mock;

        const component = mount(<FormattingForm />);
        component.find('button').simulate('click');

        return p.then(() => {
            expect(mock).toBeCalled();
        }).then(() => {
            expect(component.find('#result').getDOMNode().getAttribute('style'))
                .toBe('color: ' + expectedColor + ';');
            expect(component.find('#result').text()).toBe(expectedText);
        }).catch((reason) => {
            expect(mock).toBeCalled();
            expect(component.find('#result').getDOMNode().getAttribute('style'))
                .toBe('color: ' + expectedColor + ';');
            expect(component.find('#result').text()).toBe(expectedText);
        });
    }

    it ('renders successful response from api', 
        sample(
            (resolve, reject) => {
                resolve({
                    ok: true, 
                    text: function() {
                        return '12 345.67'
                    }
                });
            },
            'green',
            '12 345.67'
        )
    );

    it ('renders failures', sample(
            (resolve, reject) => {
                resolve({
                    ok: false, 
                    text: function() {
                        return 'incorrect input'
                    }
                });
            },
            'red',
            'incorrect input'
    ));

    it ('renders crashes', sample(
        (resolve, reject) => {
            reject('failure');
        },
        'red',
        'Something went wrong: failure'
    ));
});
