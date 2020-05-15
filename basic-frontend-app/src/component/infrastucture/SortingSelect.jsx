import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';

const useStyles = makeStyles((theme) => ({
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    },
}));

export default function ControlledOpenSelect(props) {
    const classes = useStyles();

    return (
        <div>
            {props.order.map(orderRow => (
                <FormControl key={orderRow.name} className={classes.formControl}>
                <InputLabel>{orderRow.name}</InputLabel>
                <Select
                    value={orderRow.value}
                    onChange={orderRow.handleChange}
                >
                    {orderRow.items.map(item =>
                        (
                            <MenuItem key={item.value} value={item.value}>{item.text}</MenuItem>
                        ))
                    }
                </Select>
            </FormControl>
            ))}
        </div>
    );
}
