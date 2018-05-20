import React from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField} from "material-ui";

function TextBox({val, onChange, password, label, ...rest}) {
    return (<TextField
        value={val} type={password === true ? "password" : "text"}
        margin="normal"
        label={label}
        onChange={(e) => onChange(e.target.value)}
        {...rest}
    />)
}

export const makeTextbox = (label, props1) => (({val, onChange, classes, ...props2}) => {
    return (
        <TextBox label={label}
                 val={val}
                 onChange={onChange}
                 margin="normal"
                 classes={classes}
                 {...props1}
                 {...props2}
        />
    )
});

export const makeDialog = (msg, title, props1) => (({err, onClick, ...props2}) => {
    return (
        <Dialog
            open={err}
            onClose={onClick}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
            {...props2}
            {...props1}
        >
            <DialogTitle id="alert-dialog-title">{title}</DialogTitle>
            <DialogContent>
                <DialogContentText id="alert-dialog-description">
                    {msg}
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClick} color="primary">
                    Close
                </Button>
            </DialogActions>
        </Dialog>
    )
});

