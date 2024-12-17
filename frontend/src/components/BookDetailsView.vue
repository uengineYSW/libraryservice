<template>

    <v-data-table
        :headers="headers"
        :items="bookDetails"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'BookDetailsView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            bookDetails : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/bookDetails'))

            temp.data._embedded.bookDetails.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.bookDetails = temp.data._embedded.bookDetails;
        },
        methods: {
        }
    }
</script>

