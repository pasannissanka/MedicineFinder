import { GeoSearchControl, OpenStreetMapProvider } from "leaflet-geosearch";
import { useMap } from "react-leaflet";
import { useEffect } from "react";
import "leaflet-geosearch/dist/geosearch.css";
import { LatLng } from "leaflet";

type SearchFieldProps = {
  position?: LatLng;
  setPosition: React.Dispatch<React.SetStateAction<LatLng | undefined>>;
};

export const SearchField = ({ setPosition, position }: SearchFieldProps) => {
  const provider = new OpenStreetMapProvider();
  // @ts-ignore
  const searchControl = new GeoSearchControl({
    provider: provider,
    marker: {
      draggable: true,
    },
    keepResult: true,
  });

  const map = useMap();

  useEffect(() => {
    map.addControl(searchControl);
    map.on("geosearch/marker/dragend", (e) => {
      setPosition(e.target.getLatLng());
    });
    map.addEventListener("geosearch/showlocation", (e) => {
      setPosition(e.target.getLatLng());
    });
    return () => {
      map.removeControl(searchControl);
    };
  }, []);

  return null;
};
