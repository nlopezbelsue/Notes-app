import { NavLink } from "react-router-dom";

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-inner">
        <NavLink to="/" className="nav-link">
          My notes
        </NavLink>
        <NavLink to="/archived" className="nav-link">
          Archived
        </NavLink>
        <NavLink to="/create" className="nav-link">
          New note
        </NavLink>
        <NavLink to="/categories" className="nav-link">
          Categories
        </NavLink>
      </div>
    </nav>
  );
}

export default Navbar;

