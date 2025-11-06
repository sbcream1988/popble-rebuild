import { RouterProvider } from "react-router";
import "./App.css";
import root from "./router/root";

function App() {
  return <RouterProvider router={root}></RouterProvider>;
}

export default App;
