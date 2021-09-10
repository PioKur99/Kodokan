export class MapState {
    map = new Map<string,string>();
    constructor(){
        this.map.set("NOT_PAID", 'Nieopłacona')
        this.map.set("PAID", 'Opłacona')
        this.map.set("PRINTED", 'Wydrukowana')
        this.map.set("READY", 'Do odbioru')
        this.map.set("PICKED_UP", 'Odebrana')   
    }
}
