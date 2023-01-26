import React, { useEffect } from "react";
import { MapContainer, TileLayer, useMap, Marker, Popup } from "react-leaflet";
import { SearchField } from "./SearchField";

const Map = () => {
    
  return (
    <>
      <div className="h-52 w-full my-4">
        <MapContainer
          className="h-52"
          center={[51.505, -0.09]}
          zoom={13}
          scrollWheelZoom={false}
        >
          <SearchField />
          <TileLayer
            attribution='&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <Marker position={[51.505, -0.09]}>
            <Popup>
              A pretty CSS3 popup. <br /> Easily customizable.
            </Popup>
          </Marker>
        </MapContainer>
      </div>
    </>
  );
};

export default Map;
