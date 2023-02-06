import { DragEndEvent, LatLng } from "leaflet";
import React, { useEffect, useState } from "react";
import {
  MapContainer,
  TileLayer,
  useMap,
  Marker,
  Popup,
  useMapEvents,
} from "react-leaflet";
import { SearchField } from "./SearchField";

type MapProps = {
  onChange: (e: LatLng) => void;
};

const Map = ({ onChange }: MapProps) => {
  const [position, setPosition] = useState<LatLng>();

  useEffect(() => {
    if (position) {
      onChange(position);
    }
  }, [position]);

  return (
    <>
      <div className="h-52 w-full my-4">
        <MapContainer
          className="h-52"
          center={[51.505, -0.09]}
          zoom={13}
          scrollWheelZoom={false}
        >
          <SearchField position={position} setPosition={setPosition} />
          <LocationMarker position={position} setPosition={setPosition} />
          <TileLayer
            attribution='&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
        </MapContainer>
      </div>
    </>
  );
};

type LocationMarkerProps = {
  position?: LatLng;
  setPosition: React.Dispatch<React.SetStateAction<LatLng | undefined>>;
};

function LocationMarker({ position, setPosition }: LocationMarkerProps) {
  const map = useMapEvents({
    click() {
      map.locate();
    },
    locationfound(e) {
      setPosition(e.latlng);
      map.flyTo(e.latlng, map.getZoom());
    },
  });

  const eventHandlers = {
    dragend(e: DragEndEvent) {
      setPosition(e.target.getLatLng());
    },
  };

  return position === null || position === undefined ? null : (
    <Marker eventHandlers={eventHandlers} draggable position={position}>
      <Popup>You are here</Popup>
    </Marker>
  );
}
export default Map;
