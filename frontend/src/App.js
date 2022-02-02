import React, { Component } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./App.css";
import Todos from "./components/Todos";
import Header from "./components/layout/Header";
import AddTodo from "./components/AddTodo";
import About from "./components/pages/About";
import axios from "axios";
// uuid library no longer has export default. so import version 4, v4
import { v4 as uuid } from "uuid";
// need to use className attribute rather than class attribute for HTML classes
class App extends Component {
  // seems like we store stateful info in this state object rather than declaring attributes in constructor?
  state = {
    todos: [],
  };

  componentDidMount() {
    // .get() is a promise i think
    axios
      .get("http://localhost:8080/todo/list")
      .then((res) => this.setState({ todos: res.data }));
  }

  //Toggle complete
  markComplete = (id) => {
    axios.put(`http://localhost:8080/todo/toggle/${id}`).then((res) => {
      this.setState({
        todos: this.state.todos.map((todo) => {
          if (todo.id === id) {
            //Toggle completed attribute
            todo.completed = !todo.completed;
          }
          return todo;
        }),
      });
    });
  };

  //Delete Todo
  // can use spread operator too i guess
  // todos: [...this.state.todos.filter(todo => todo.id !== id)]
  // ... spread operator takes this.state.todos array and spreads it into individual
  // elements to be saved in new array defined by outer square brackets []
  delTodo = (id) => {
    axios
      .delete(`http://localhost:8080/todo/delete/${id}`)
      .then((res) =>
        this.setState({
          todos: this.state.todos.filter((todo) => todo.id !== id),
        })
      );
  };

  //Add Todo
  addTodo = (title) => {
    axios
      .post("http://localhost:8080/todo/add", {
        title: title,
        completed: false,
      })
      .then((res) => this.setState({ todos: [...this.state.todos, res.data] }));
  };

  render() {
    return (
      <Router>
        <div className="App">
          <div className="container">
            <Header />

            <Routes>
              <Route
                path="/"
                element={
                  <React.Fragment>
                    <AddTodo addTodo={this.addTodo} />
                    <Todos
                      todos={this.state.todos}
                      markComplete={this.markComplete}
                      delTodo={this.delTodo}
                    />
                  </React.Fragment>
                }
              />

              <Route path="/about" element={<About />} />
            </Routes>
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
