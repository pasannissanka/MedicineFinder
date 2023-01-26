import { GeoSearchControl, OpenStreetMapProvider } from "leaflet-geosearch";
import { useMap } from "react-leaflet";
import { useEffect } from "react";
import 'leaflet-geosearch/dist/geosearch.css';


export const SearchField = () => {
  const provider = new OpenStreetMapProvider();
  // @ts-ignore
  const searchControl = new GeoSearchControl({
    provider: provider,
  });

  const map = useMap();

  
  useEffect(() => {
    map.addControl(searchControl);
    map.addEventListener("geosearch/showlocation", (e) => {
      console.log(e)
    })
    return () => map.removeControl(searchControl);
  }, []);

  return null;
};
