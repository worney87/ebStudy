import React, {Component} from 'react';
import ControlSection from './ControlSection'
import BoardSection from "./BoardSection";

class MainSection extends Component {
    render() {
        return (
            <div>
                <section><ControlSection/></section>
                <section><BoardSection/></section>
            </div>
        );
    }
}

export default MainSection;