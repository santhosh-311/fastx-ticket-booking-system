import './App.css';
import Header from './components/Header';
import Home from './components/Home';
import Routing from './components/Routing';
import { DataProvider } from './components/context/DataContext';



function App() {
  return (
    <div className="min-h-screen bg-gray-100 app">
      <DataProvider>
        <Routing/>
      </DataProvider>
    </div>
  );
}

export default App;
