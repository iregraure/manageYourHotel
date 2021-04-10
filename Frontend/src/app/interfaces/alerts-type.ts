// Interface to define the dialog's type
export interface AlertsType
{
    type: number,
    text: string
}

// Class to define the types of dialogs and button's response
export class Types
{
    public static readonly WAITING = 1;
    public static readonly ERROR = 2;
    public static readonly CONFIRM = 3;
    public static readonly INFO = 4;

    public static readonly CANCEL = 0;
    public static readonly ACCEPT = 1;
}