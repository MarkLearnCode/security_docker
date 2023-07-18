<template>
    <button @click="add">add</button>
    <button @click="reduce">reduce</button>
    <h1>count {{ count }}</h1>
    <h1>allClick {{ allClick }}</h1>
    <div v-if="count > 5">
        <h1>count>>>>>5</h1>
    </div>
</template>
<script>
export default {
    data() {
        return {
            count: 0,
            allClick: 0
        }
    },
    methods: {
        add() {
            this.count++
            this.allClick++
        },
        reduce() {
            if (this.count > 0) {
                this.count--
            }
            this.allClick++
        }
    }
}
</script>
