export interface Message {
    id: number;
    senderId: number;
    senderProfile: any;
    sentToId: number;
    chatId: number;
    messageDate: Date;
    text: string;
    mediaId: number;
    media: any;
    created: Date;
    updated: Date;
    disabled: boolean;
    deleted: boolean;
}
