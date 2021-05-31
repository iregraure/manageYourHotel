export interface Room
{
    number: number;
    type: String;
    price: number;
    smoker: boolean;
    tv: boolean;
    airConditioning: boolean;
    breakfast: boolean;
    floorNumber: number;
    buildingName: String;
    copyRoom?: number;
    state: string;
}