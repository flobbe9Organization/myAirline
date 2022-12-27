import express from "express";


const app = express();

const port = 4002;


app.use(express.json());


app.listen(4002, () => console.log(`Listening to port ${port}.`));